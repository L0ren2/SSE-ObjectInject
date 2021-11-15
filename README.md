# SSE-ObjectInject

#### What is this?
This is a neat little setup for demonstrating how ObjectInjection works in Java

#### Where Java?
All Sourcefiles are in ObjInj/src.

#### How to use?
The Project consists of a client and a server. The server has a cookie object.
The client can send messages to the server. When the server receives a message, it checks whether it is a serialized cookie. If this is the case, the server will attempt to create a new cookie object from the serialized data. If the message is not usable for creating a new cookie, the server will instead convert it to a string and print it to the console.
When sending `print_cookie` from the client to the server, the server will print out the current cookie's values `username` and `role`.

#### How does the actual exploit work?
The cookie has the fields `username` and `role`. `username` is a string, `role` is an enum which can have values `normal_user` and `admin`. The server uses the last four bytes of every message to determine whether a message is a cookie. It does that using the following strategie: If a message ends with the bytes `0x63 0x6F 0x6F` (ascii 'coo'), the server thinks it is a cookie. The fourth byte from the back is used for setting the cookie's `role`. If this byte is set to `0x31` (ascii '1'), the role will be set to `admin`. The rest of the data will be used to initialize `username`. 

#### Example
```
$ print_cookie
#>print_cookie
#>Username: Cookie
#>Role: normal User

$ 3vilC00ki31coo

$ print_cookie
#>print_cookie
#>Username: 3vilC00ki3
#>Role: Admin
```

#### How to download and use?
This one is easy. Just clone the repo and import ObjInj as a new Java project in Eclipse or use whatever IDE / Editor you like.

