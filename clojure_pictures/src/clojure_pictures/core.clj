(ns clojure_pictures.core
  (:gen-class))

(use 'mikera.image.core)
(use '[clojure.string :only (split)])

(def crypto-maps {})
(def buffer-picture-first)

(def file-with-cryptocode 
  (clojure.java.io/reader (clojure.java.io/file "cryptocode.txt")))

(defn read-file 
  [file-with-cryptocode]
  (line-seq file-with-cryptocode))

(def read-raw-cryptocode 
  (read-file file-with-cryptocode))

(defn to-int [x]
 (java.lang.Integer/parseInt x)) 

(defn read-crypto-data [contents-file]
  (reduce (fn [acc row]
            (let [[crypto-number crypto-mark] (clojure.string/split row #"'")]
              (assoc acc (to-int crypto-number)
                     {:id (to-int crypto-number)
                      :name crypto-mark})))
          {} contents-file))


(def crypto-data (read-crypto-data read-raw-cryptocode))

(def crypto-maps {})
(def buffer-picture-first)

(defn insert-in-picture
  "Insert in picture"
  [buffer-picture]
  (loop [index-crypto-maps 0 index-width-picture 0]
    (when (< index-crypto-maps (count crypto-maps))
      (set-pixel buffer-picture index-width-picture 1 (+ (get-pixel buffer-picture index-width-picture 1)(to-int (str (first (str (get (get crypto-maps index-crypto-maps) :id)))))))
      (set-pixel buffer-picture (+ index-width-picture 1) 1 (+ (get-pixel buffer-picture (+ index-width-picture 1) 1)(to-int (str (second (str (get (get crypto-maps index-crypto-maps) :id)))))))
      (recur (+ index-crypto-maps 1) (+ index-width-picture 2) )))
  (show buffer-picture :zoom 1.0 :title "This is your encrypted pictures!"))

(defn encoding-message
  "Enconding message"
  [secret-message-x]
  (loop [index-sign 0]
    (when (< index-sign (count secret-message-x))
      (loop [index-crypto-data 10]
        (when (< index-crypto-data (+ (count crypto-data) 10))
          (if (= (str (get secret-message-x index-sign))(get (get crypto-data index-crypto-data) :name))
            (def crypto-maps (assoc crypto-maps index-sign {:id (get (get crypto-data index-crypto-data) :id)})))
          (recur (+ index-crypto-data 1))))
      (recur (+ index-sign 1))))
  (insert-in-picture buffer-picture-first)) 

(defn check-pixels
  "Verify that the pixels of the pictures are same"
  [picture-first picture-second width height]
(let [pixel1 (get-pixel picture-first width height)
      pixel2 (get-pixel picture-second width height)]
  (if (not (= pixel1 pixel2))
    (do 
      (println (str "Coordinates: " width " " height " " pixel1 " " pixel2 " " (str (- pixel2 pixel1))))
      (def messenger "abc")))))

(defn check-pixels-loop
  "prepare arguments for calling methods check-pixels and gives a final solution"
  [picture-first picture-second]
  (loop [width1 0]
    (when (< width1 (width picture-first))
    (loop [height1 0]
      (when (< height1 (height picture-first))
          (check-pixels picture-first picture-second width1 height1)
        (recur (+ height1 1))))
      (recur (+ width1 1))))
  (if (string? messenger)
    (println "The pictures are different!")
    (println "The pictures are the same!")))

(defn check-height
  "Verify that the height of the pictures are same"
  [picture-first picture-second]
  (if(= (height picture-first) (height picture-second))
    (do
      (println (str "Height is same: " (height picture-first)))
      (check-pixels-loop picture-first picture-second))
    (do
      (println (str "Height is differently: " " H1 = " (height picture-first) " H2 = " (height picture-second)))
      (println (str "The pictures are different!")))))

(defn check-width
  "Verify that the width of the pictures are same"
  [picture-first picture-second]
  (if (= (width picture-first)(width picture-second))
    (do
      (println (str "Width is same: " (width picture-first)))
      (check-height picture-first picture-second))
    (do
      (println (str "Width is differently: " " W1 = " (width picture-first) " W2 = " (width picture-second)))
      (println (str "The pictures are different!")))))

(defn get-input 
  "Take user's input and convert it to lowercase"
  ([] (get-input "")) 
  ([default] 
    (let [input (clojure.string/trim (read-line))] 
      (if (empty? input) 
        default 
        (str input))))) 

(defn upload-pictures
  "Enter the path to first picture and enter the path to second/crypt picture"
  []
  (println "Enter the path to the basic picture:")
  (let [path (String. (get-input 200))] 
    (def buffer-picture-first (load-image path)))
  (println "Enter the path to the second/crypt picture:")
  (let [path (String. (get-input 200))] 
    (def buffer-picture-second (load-image path)))
  (check-width buffer-picture-first buffer-picture-second)) 

(defn upload-pictures-and-message
  "Upload pictures and message"
  []
  (println "Enter the path to the picture:")
  (let [path (String. (get-input 200))]
    (def buffer-picture-first (load-image path)))
  (println "Enter the message, save (File - Save as) and close the picture")
  (let [message (String. (get-input 200))] 
    (def secret-message message) 
    (encoding-message secret-message)))

(defn selection-mod-work
  "Choose whether you want to encrypt or decrypt"
 []
 (println "1 - Decoding")
 (println "2 - Encoding")
 (let [choice (String. (get-input 1))]
   (if (= "1" choice)
     (upload-pictures)
     (if (= "2" choice)
       (upload-pictures-and-message)
       (println "Error while selecting modes!")))))

 (defn -main 
   [& args] 
   (println "Welcome to my programm!")
 (println "Select mode:")
   (selection-mod-work)) 
