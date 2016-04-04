;--------------------------------------------------------------------------------
;in cmd
;run tests: lein midje
;run autotest: lein midje :autotest
;---------------------------------------------------------------------------------

(ns clojure-pictures.core-test
  (:use midje.sweet)
  (:use [clojure.java.io :only (file)])
  (:use [clojure_pictures.core])  
  (:import javax.imageio.ImageIO))

(use 'mikera.image.core)

; I midje test
; test metod to-int
(fact (to-int "1") => 1)

; II midje test
; test metod read-file and cryptocode.txt

(def file-with-cryptocode-test 
  "takes a pointer to a file"
  (clojure.java.io/reader (clojure.java.io/file "cryptocode.txt")))

(fact (read-file file-with-cryptocode-test) => '("11'0'" "12'1'" "13'2'" "14'3'" "15'4'" "16'5'" "17'6'" "18'7'" "19'8'" 
                                                         "21'9'" "22'A'" "23'B'" "24'C'" "25'D'" "26'E'" "27'F'" "28'G'" 
                                                         "29'H'" "31'I'" "32'J'" "33'K'" "34'L'" "35'M'" "36'N'" "37'O'" 
                                                         "38'P'" "39'Q'" "41'R'" "42'S'" "43'T'" "44'U'" "45'V'" "46'W'" 
                                                         "47'X'" "48'Y'" "49'Z'" "51'a'" "52'b'" "53'c'" "54'd'" "55'e'" 
                                                         "56'f'" "57'g'" "58'h'" "59'i'" "61'j'" "62'k'" "63'l'" "64'm'" 
                                                         "65'n'" "66'o'" "67'p'" "68'q'" "69'r'" "71's'" "72't'" "73'u'" 
                                                         "74'v'" "75'w'" "76'x'" "77'y'" "78'z'" "79'!'" "81'%'" "82'('" 
                                                         "83')'" "84'*'" "85'+'" "86','" "87'-'" "88'.'" "89'/'" "91':'" 
                                                         "92';'" "93'?'" "94'@'" "95'='" "96'|'" "97'\\'" "98' '"))
; III midje test
(def read-raw-cryptocode-test 
  (read-file file-with-cryptocode-test))

(fact (read-crypto-data read-raw-cryptocode-test) => {})

(def crypto-data-test (read-crypto-data read-raw-cryptocode))

; IV midje test
; test metod check-pixels

(def path1 "picture-first/smile.jpg")
(def path2 "picture-second/smile.jpg")
(def path3 "picture-first/smile2.jpg")

(def buffer-picture-first1 (ImageIO/read (java.io.File. path1)))
(def buffer-picture-second1 (ImageIO/read (java.io.File. path2)))
(def buffer-picture-three1 (ImageIO/read (java.io.File. path3)))

(fact (check-pixels buffer-picture-first1 buffer-picture-first1 1 1) => nil)

; V midje test
(fact (check-pixels buffer-picture-first1 buffer-picture-second1 1 1) => "7")

; VI midje test
(fact (check-pixels buffer-picture-first1 buffer-picture-three1 1 1) => "-11806219")

; VII midje test
; test metod decoding
(def crypto-maps-test {})
(fact (decoding) => nil)

; VIII midje test
; test metod check-pixels-loop
(fact (check-pixels-loop buffer-picture-first1 buffer-picture-second1) => nil)

; IX midje test
; test metod check-height
(fact (check-height buffer-picture-first1 buffer-picture-three1) => nil)

; X midje test
; test metod check-width
(fact (check-width buffer-picture-first1 buffer-picture-three1) => nil)




