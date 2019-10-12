

## Generating the Protobuf Java code
Run

```
protoc -I=. --java_out=src/main/java src/main/resources/address-book.proto
```

from the root of the project to generate the Java code.

## Source
https://developers.google.com/protocol-buffers/docs/javatutorial