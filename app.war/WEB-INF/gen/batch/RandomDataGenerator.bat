@echo off

set MYCLASS=.

for %%i in (..\..\..\\WEB-INF\lib\*.jar) do call ClassAppender %%i

java -cp %MYCLASS% -DCNI.HOME=..\..\.. com.cni.fw.db.util.RandomDataGenerator %1 %2