@echo off

set MYCLASS=.

for %%i in (..\..\..\WEB-INF\lib\*.jar) do call ClassAppender %%i

java -cp %MYCLASS% -DCNI.HOME=..\..\.. com.cni.fw.ui.console.meta.cmd.MetaTransformer %1 %2 %3 %4 %5 %6 %7 %8 %9