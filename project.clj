(defproject calcobot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]
                 [cheshire "5.5.0"]
                 [overtone/at-at "1.2.0"]]
  :plugins [[lein-ring "0.8.13"]]
  :main ^:skip-aot calcobot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
