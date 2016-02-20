# clojure_pictures

The application is used for encoding and decoding secret message into image. 

## Installation

Download from http://example.com/FIXME.
## Requirements

The application requires Leiningen 2.0.0 or later.

## Usage

To start application, open command line, and navigate to the application folder. Then input command: lein run and follow the instructions.

 If we want encoding message:

1.	Put 2, press Enter
2.	Give path to first basic picture where we want write secret message, press Enter
3.	Put secret message, press Enter
4.	We get window with our picture, and we must choose File -> Save as. We write name for our new picture with secret message and save.

If we want decoding message:

1.	Put 1, press Enter
2.	Give path to first basic picture and path to second picture where we write secret message, press Enter
3.	Application check if the images are the same and check is there a secret message. If there are a secret message, it write a secret message.


    $ java -jar clojure_pictures-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
