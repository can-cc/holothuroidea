(require '[cljs.build.api :as b])

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
    {:main 'holothuroidea.core
     :output-to "out/holothuroidea.js"
     :output-dir "out"
     :target :nodejs
     :verbose true
     :cache-analysis true
     :pretty-print true})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))


