#Ktor + Thrift experiments

When in doubt, follow the commit history to understand what's going on.

### Main objective

Experiment in the current ktor setup ways to streamline and improve serving a Thrift API. HTTP over Ktor not ideal, I believe.

I'm not happy with the current usage of Armeria over HTTP (I honestly just wanted to do it socket based), but I hit my time limit.

### Running the project

Run the main function in `Application.kt`

There's no security implemented (yet)
