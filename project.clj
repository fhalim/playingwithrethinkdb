(defproject funwithrethinkdb "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.rethinkdb/rethinkdb-driver "2.3.2"]]
  :main ^:skip-aot funwithrethinkdb.core
  :target-path "target/%s"
  :plugins [[lein-cljfmt "0.5.3"]]
  :profiles {:uberjar {:aot :all}})