# KPM Prototype using a swift package manager dependency that exposes an Objective-C API in a Kotlin Multiplatform iOS target.

## Idea

To achieve this, my idea was to:
- use cinterop to use the objective-c headers exposed by the swift package manager dependency in kotlin.
  
To do so, I intended on creating a `.def` file that would point to the public headers of the swift package manager dependency, and then use the `cinterop` command to generate the kotlin bindings for the objective-c code.

For a starting point, I decided to create a dummy swift package exposing a simple Objective-C API, just so I could test the cinterop feature and put it all together in a KMM iOS target, before integrating a real SwiftPM dependency.
To generate the bridging headers, I used the `swiftklib` tool, which simplifies the process of generating `.klib` interop libraries from SwiftPM packages, in order to avoid that configuration in the beginning of the project, leaving it for later.
With this, I should be able to call the API from kotlin code through the generated bindings, and then use it in an iOS app.

## Problems Encountered

I had several problems while trying to implement this task, which I will list below:
1. These past 2 weeks I have had a lot of exams, project presentations and deliverables, which consumed a lot of my time, leading to starting it late and not being able to finishing it. I now have some breathing room, to continue working on it, which I intend on doing if there is still time left.
2. I can run Linux or Windows with my setup, but I cannot run macOS and therefore can't use Xcode or compile Swift code, which makes it hard for me to compile the ios Target and test it. I was able to borrow a Mac, but with limited time  which makes it hard to test the code and the idea.
3. Understand exactly how cinterop works, what is required, where are the files stored and how to integrate it into gradle workflow
4. I had to learn how to use SwiftPM, and get acquinted with the structure of KMM projects and the cinterop feature, which took some time.


## What I learned

Even though, I have only been able to implement a prototype, with a dummy package, I learned a lot about the process of integrating SwiftPM dependencies into KMM iOS targets, namely about .h .m and .def files, and how to use the `cinterop` feature to generate a klib

- How to create a SwiftPM package that exposes an Objective-C API using swiftklib.
- How to use the `cinterop` feature to generate Kotlin bindings for Objective-C code.
- How to generate `klib` files from SwiftPM packages using `swiftklib`, avoiding the need to manually configure `.def` files.
- that integrating swift packages into a KMM iOS target, has a clear process, that if streamlined can bring a lot of value to the KMM ecosystem.

## Next steps

- Use a real swift package that exposes an Objective-C API with public headers and swap the dummy out. 
- Try and createcreate `.def` files myself, instead of using `swiftklib`.
- Finish the ios app, and test the integration with the swift package
- Maybe write some tests to ensure the integration works as expected.

## Conclusion

Even though I was not able to finish the task, I believe I have a good starting point and should be abe to complete it in 1-2 days. If there is still time left, I want to finish the task and hopefully submit wa more detailed report with a working prototype, using a real SwiftPM package and maybe even not use `swiftklib` to generate the `.klib` files, but instead use the manual process of creating `.def` files and generating the bindings myself.
