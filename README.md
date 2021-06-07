### MOVIE FRAME SAVER

This is a simple program to split a movie file by its frames and save the first ten of them as images.
It accepts as arguments the name of the video file with its extension as the first argument, and the output path of the
images as the second argument.  
  
#### Example
`java -jar movie-frames-saver-1.0-SNAPSHOT-jar-with-dependencies.jar "bunny.webm" "/home/luisalves/Personal/Learn/Java/movie-frame-splitter/src/main/resources/movies"`

#### Installation
To use the program you need to compile it using maven command tool. It has a build plugin that packages all dependencies
inside a single jar: `mvn clean package`. It can take several minutes so take a coffee! After its done you can find 
the jar file inside the target directory.

#### Running it  
The program can be run from where it is, or you can move the jar to place of your liking. Take in mind that the video file
must be in the same directory of the jar file. Don't forget to place between `"`. To run it you can use as example the 
command given above, in the example chapter.  
There are some video file in the root of the source code, so that you can test it.
