(ns test.core
  (:require [cljs.test :as test :refer-macros [deftest is testing run-tests]]
            [cljs.nodejs :as nodejs]
            [test.build]))

(nodejs/enable-util-print!)

(defmethod cljs.test/report [:cljs.test/default :end-run-tests] [m]
  (if (cljs.test/successful? m)
    (println "@Success@")
    (binding [*print-fn* *print-err-fn*]
      (println "<-=Failure-=>"))))

(defmethod cljs.test/report [:cljs.test/default :fail] [m]
  (binding [*print-fn* *print-err-fn*]
    (test/inc-report-counter! :fail)
    (println "\nFAIL in" (test/testing-vars-str m))
    (when (seq (:testing-contexts (test/get-current-env)))
      (println (test/testing-contexts-str)))
    (when-let [message (:message m)] (println message))
    (test/print-comparison m)))

(defmethod cljs.test/report [:cljs.test/default :error] [m]
  (binding [*print-fn* *print-err-fn*]
    (test/inc-report-counter! :fail)
    (println "\nFAIL in" (test/testing-vars-str m))
    (when (seq (:testing-contexts (test/get-current-env)))
      (println (test/testing-contexts-str)))
    (when-let [message (:message m)] (println message))
    (test/print-comparison m)))

(defmethod cljs.test/report [:cljs.test/:default :summary] [m]
  (println "\nRan" (:test m) "tests containing"
           (+ (:pass m) (:fail m) (:error m)) "assertions.")
  (println (:fail m) "failures," (:error m) "errors."))

(cljs.test/run-tests)
(cljs.test/run-tests 'test.build)
