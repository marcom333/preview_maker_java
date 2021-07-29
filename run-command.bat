set arg1=%1
set arg2=%2
g:/ffmpeg/ffmpeg -hwaccel nvdec -i %arg1% -vf fps=1/%arg2% "prev/img%%d.jpg"