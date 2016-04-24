(ns clojuretest.core
  (:import (org.apache.commons.math3.distribution BinomialDistribution
                                                  ExponentialDistribution
                                                  AbstractIntegerDistribution)))

;; Converts fraction into a float
(float
  ;; Calculates the average of the vector (fraction)
  ((fn [x] (/ (apply + x) (count x)))
	  ;; Converts true false to 1 0
	  (map (fn [x] (if x 1 0))
		  ;; Returns a vector of true false
		  (map (fn [x] (> x 50000))
			  ;; Returns a vector of sums (number of simulations)
			  (repeatedly 100000 ;; 100,000 simulations
				  ;; Returns the sum of the claims
				  #(reduce + 
					  ;; Returns a random number of random claims
					  (repeatedly (. (new BinomialDistribution 1000 0.05) inverseCumulativeProbability (rand)) 
                        (fn [] (. (new ExponentialDistribution 800) sample)))
				   )
			  )
		  )
	  )
  )
) ;; returns a value approximately = 0.10685
