# KPM Prototype using a swift package manager dependency that exposes an Objective-C API in a Kotlin Multiplatform iOS target.

## Idea

In order to use a swift package manager dependency in KMP target, my idea was to:

- use kmps cinterop to create the objective-c headers int   o  exposed by the swift package manager dependency in the kotlin code.
  
To do so, I intended on creating a `.def` file that would point to the public headers of the swift package manager dependency, adding a step in the compilation process to use `cinterop`, that I expected to generate the bindings between the Objective-C API and Kotlin code, allowing me to call the API from Kotlin code in the iOS target.

## Approach

1. Created a new KMP project using a KMP template from [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com/) .

As I was not familiar with how to integrate a SwiftPM dependency into kotlin I decided to start witwh a dummy swift package so that I could make it as simple as possible. 
2. Created a Swift package that exposes an Objective-C API, using the `swiftklib` tool to generate the header files which would be used by the cinterop feature to create the package bindings.


After doing this, I tried to integrate an actual swift package.
It should expose Objective-C API, I chose a package [afnetworking](https://github.com/AFNetworking/AFNetworking/tree/master).

1. Used Xcode to add the dependency to the ios target of the KMP project
2. Copied the .h files from the swift package to the kmp project
3. Created a `.def` file pointing to the headers
```
language = Objective-C
headers = $(proj_dir)/composeApp/src/nativeInterop/cinterop/afnetworking_include/AFNetworking.h
package = org.example.pedro.afnetworking

compilerOpts = -fno-modules -Iafnetworking_include
linkerOpts = -framework AFNetworking
```
4. Added the cinterop task in `build.gradle.kts` pointing to the `.def` file and with the same package name there
5. Asked ChatGPT to generate a sample usage of the API after the cinterop task was run


## Problems Encountered

I had several problems while trying to implement this task, which I will list below:

1. I had to learn how to use SwiftPM, and get acquinted with the structure of KMM projects and the cinterop feature, which took some time.
2. Finding a real SwiftPM dependency that exposes an Objective-C API was harder than I expected, I am yet to find a way of filtering in the swift package index to find such dependencies. I tried searching for "Objective-C" but found it hard to find a package with publicHeadersPath set in the `Package.swift` file. I would love feedback on this part as I believe it should be easier.
3. Fully understanding which properties and how to set them in the `.def` file. I got it to work but I believe some improvements could be made.
4. I found it hard to understand the best practice for importing the dependency headers into the kmp project. I ended up copying the headers from xcode derived data to the project, but I am sure there must be a better way of doning this. 

## What I learned

- How to use a Swift package in a KMP iosTarget using  `swiftklib`
- How to use the `cinterop` feature to generate Kotlin bindings for Objective-C code. defining a `.def` file with the correct properties and a compilation step in the `build.gradle.kts` file.
- An introduction on the structure of a KMP project and xcode


## Next steps

- Find a way of filtering swiftPM dependencies that expose Objective-C APIs to assess if they are suitable for using in a KMP project.
- Explore more properties for .def files
- Learn how .h files are generated when importing a SwiftPM dependency in Xcode, and what is the best way to include them in the KMP project, available for the .def file.
