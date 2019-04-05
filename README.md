# use-buttle

Example project that shows how to use _Buttle_ [1] as a lib.

[1] https://github.com/henrik42/buttle

## Examples

In `src/use_buttle/core.clj` you can control, which method invocations
get logged. For this you need a running Postgres.

	lein run jdbc:postgresql://127.0.0.1:6632/postgres <user> <password>

