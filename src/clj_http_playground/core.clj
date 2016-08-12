(ns clj-http-playground.core
  (:require [clj-http.client :as http]
            [clj-http.cookies :as cookies])
  (:import [org.jsoup Jsoup]))

(defn fetch-api-key [email password]
  (let [cookie-store (cookies/cookie-store)
        login-page (http/get "https://developer.forecast.io/log_in"
                     {:cookie-store cookie-store})
        login-doc (-> login-page
                    :body
                    Jsoup/parse)
        login-form (-> login-doc
                     (.select "#login-form")
                     first)
        token (-> login-form
                (.select "[name=\"authenticity_token\"]")
                first
                (.attr "value"))
        page (http/post "https://developer.forecast.io/sessions"
               {:force-redirects true
                :cookie-store cookie-store
                :form-params {:utf8 "✓"
                              :authenticity_token token
                              :email email
                              :password password
                              }})
        page-doc (-> page
                   :body
                   Jsoup/parse)
        api-key (-> page-doc
                  (.select "#api_key")
                  first
                  (.attr "value"))]
    api-key))

;;https://developer.forecast.io/users/reset_api_key

(defn reset-api-key [email password]
  (let [cookie-store (cookies/cookie-store)
        login-page (http/get "https://developer.forecast.io/log_in"
                     {:cookie-store cookie-store})
        login-doc (-> login-page
                    :body
                    Jsoup/parse)
        login-form (-> login-doc
                     (.select "#login-form")
                     first)
        token (-> login-form
                (.select "[name=\"authenticity_token\"]")
                first
                (.attr "value"))
        page (http/post "https://developer.forecast.io/sessions"
               {:force-redirects true
                :cookie-store cookie-store
                :form-params {:utf8 "✓"
                              :authenticity_token token
                              :email email
                              :password password
                              }})
        response (http/get "https://developer.forecast.io/users/reset_api_key"
                   {:cookie-store cookie-store
                    :as :json})]
    (:body response)))
