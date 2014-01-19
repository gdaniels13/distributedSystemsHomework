This is written in nodejs/javascript and is packaged up using a library called node-webkit (more information can be found here https://github.com/rogerwang/node-webkit)

In short node-webkit is a sort of packaging wrapper around webkit and nodejs allowing the whole desktop application to be built with javascript and html/css

To compile it together and run it instructions can be found here https://github.com/rogerwang/node-webkit/wiki/How-to-package-and-distribute-your-apps

I didnt include  a compiled version because it is quite large (~40 mb) if you want to run it and have trouble let me know and i can send you a compiled version.

All the relevent code is in index.html and in /js  the rest is simply files for node-webkit and hostInfo.dat is where the host information is stored to be retrieved the next time the program is launched (i havent tested this in the compiled version)
