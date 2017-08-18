(ns holothuroidea.core
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as string]
            [holothuroidea.build :as build]
            [holothuroidea.config :as config]
            [holothuroidea.create :as new]))

(nodejs/enable-util-print!)

(defn show-banner []
  (println "HOLOTHUROIDEA"))

(defn show-help []
  (println (string/join "\n"
                 ["Usage:"
                  "seed [command]"])))

(defn -main [& args]
  (let [command (first args)]
    (cond
      (= command "build") (build/build-tree! (rest args))
      (= command "config") (config/config (rest args))
      (= command "new") (new/new-experience (rest args))
      :else (show-help))
    ))

(set! *main-cli-fn* -main)
