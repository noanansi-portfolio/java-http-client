# java-http-client
Simple Java HTTP client with Resilience4j

## Setup

### Code style
This project uses Checkstyle to enforce a common coding style. Also, to prevent unformatted files from being pushed, the
pre-commit hook is configured. Please, configure the hooks' folder the following command using:

```sh
git config core.hooksPath .githooks
```
### Checkstyle config
#### Code inspection
In IntelliJ, you can configure the code inspection to use the same file used in the CI pipeline.

![Code Inspector](https://user-images.githubusercontent.com/34351043/125009349-c7381b00-e03a-11eb-8dfb-0236a3d48fd2.png)

#### Auto formatting
In IntelliJ, you can configure the auto-formatting shortcut to use the same file used in the CI pipeline.
![Auto Formatting](https://user-images.githubusercontent.com/34351043/125009345-c606ee00-e03a-11eb-9d8b-3b035ed229c6.png)