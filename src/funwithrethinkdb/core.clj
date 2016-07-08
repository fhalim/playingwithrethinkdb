(ns funwithrethinkdb.core
  [:import [com.rethinkdb RethinkDB]
    [com.rethinkdb.gen.exc ReqlRuntimeError]]
  (:gen-class))

(defn -main
  "Fun with RethinkDB API"
  [& args]
  (with-open [conn (.. RethinkDB/r connection connect)]
    (try
      (-> RethinkDB/r
        (.dbCreate "marvel")
        (.run conn))
      (catch ReqlRuntimeError e (str "caught exception: " (.getMessage e))))
    (try
      (-> RethinkDB/r
        (.db "marvel")
        (.tableCreate "heroes")
        (.run conn))
      (catch ReqlRuntimeError e (str "caught exception: " (.getMessage e))))
    (-> RethinkDB/r
      (.db "marvel")
      (.table "heroes")
      (.insert  (-> RethinkDB/r (.hashMap "id", 1)))
      (.run conn)))
  (println "Done"))
