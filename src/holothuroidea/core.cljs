(ns holothuroidea.core
  (:require [holothuroidea.build :as build]
            [cljs.nodejs :as nodejs]
            [clojure.string :as string]))

(nodejs/enable-util-print!)

(defn show-banner []
  (println "EXPERIENCE WALL"))

(defn show-help []
  (println (string/join "\n"
                 ["Usage:"
                  "seed [command]"])))


(defn -main [& args]
  (let [command (first args)]
    (cond
      (= command "build") (build/build-tree! (rest args))
      :else (show-help))
    ))

(set! *main-cli-fn* -main)
