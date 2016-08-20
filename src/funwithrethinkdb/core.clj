(ns funwithrethinkdb.core
  [:import [com.rethinkdb RethinkDB]
   [com.rethinkdb.gen.exc ReqlRuntimeError]]
  (:gen-class))

(defn ign [f]
  (try
    (f)
    (catch ReqlRuntimeError e (str "caught exception: " (.getMessage e)))))

(defn -main
  "Fun with RethinkDB API"
  []
  (let [r RethinkDB/r]
    (with-open [conn (.. r connection connect)]
      (ign #((-> r
                 (.dbCreate "marvel")
                 (.run conn))))
      (ign
       #(-> r
            (.db "marvel")
            (.tableCreate "heroes")
            (.run conn)))
      (-> r
          (.db "marvel")
          (.table "heroes")
          (.insert (-> r (.hashMap "id", 2) (.with "name", "bob")))
          (.run conn))
      (let [res (map #(into {} %) (-> r
                                      (.db "marvel")
                                      (.table "heroes")
                                      (.run conn)))]
        (println res))))

  (println "Done"))