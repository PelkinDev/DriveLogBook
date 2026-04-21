# DriveLogBook
This is a small logbook for managing your car journeys.

### used technology
- Java-Spring-Boot
- docker
- PostgreSQL
- Slf4j

### endpoints
| mapping | endpoint                  | function                                                  |
|---------|---------------------------|-----------------------------------------------------------|
| GET     | api/v1/vehicles           | get all vehicles                                          |
| POST    | api/v1/vehicles           | create a new vehicle (json body)                          |
| GET     | api/v1/vehicles/**?**     | get vehicle with **?** id                                 |
| PATCH   | api/v1/vehicles/**?**        | update vehicle with **?** id (json body)                  |
| DELETE  | api/v1/vehicles/**?**        | delete vehicle with **?** id                              |
| GET     | api/v1/driveLogs          | get all drive logs from all vehicles                      |
| GET     | api/v1/vehicles/**?**/driveLogs | get all drive logs from vehicle with **?** id             |
| POST    | api/v1/vehicles/**?**/driveLogs | create a new drive log for specific vehicle with **?** id |



