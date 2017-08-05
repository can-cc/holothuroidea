(require '[cljs.test :as t])
(require '[cljs.build.api :as b])
(use '[clojure.java.shell :only [sh]]
     '[io.aviso.ansi])

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "test"
           {:main 'test.core
            :output-to "test-out/test.js"
            :output-dir "test-out"
            :target :nodejs
            ;; :watch-fn (fn [] (println "hi"))
            ;; :watch-fn (fn [] (println (bold-green (:out (sh "node" "test-out/test.js")))))
            :pretty-print true})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
