(ns holothuroidea.add
  (:require [cljs.nodejs :as node]
            [clojure.string :as string]
            ))

(def fs (node/require "fs"))

(defn add-experience [rest]
  (let [filename (first rest)
        filepath (str filename ".md")
        title (or (second rest) filename)]
    (->> (.openSync fs filepath, "w")
         (.closeSync fs))
    (.writeFileSync
     fs
     filepath
     (string/join "\n"
                  [(str "--title:" title)
                   (str "--date:" (.toLocaleString (js/Date.) "zh"))
                   "--tag:"
                   "###"]))))
