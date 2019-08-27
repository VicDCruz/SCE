set path=C:\Program Files\Java\jdk1.8.0_91\bin;%path%
set classpath=Simulator\bin\;C:\Program Files\Java\jdk1.8.0_91\bin;%classpath%
FOR /L %%k IN (1,1,4) DO start "Proceso "%%k cjk.bat
