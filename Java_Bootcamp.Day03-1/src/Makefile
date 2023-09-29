TRASH= ex00/*.class ex01/*.class ex02/*.class ex03/*.class ex04/*.class ex05/*.class ex03/down*
.PHONY: clean ex00 ex01 ex02 ex03
clean:
	rm -rf $(TRASH)

ex00:
	@javac ex00/Program.java
	@java -cp . ex00.Program --count=50

ex01:
	javac ex01/Program.java
	java -cp . ex01.Program --count=50

ex02:
	javac ex02/Program.java
	java -cp . ex02.Program --arraySize=13 --threadsCount=3

ex03:
	javac ex03/Program.java
	java -cp . ex03.Program --threadsCount=3
