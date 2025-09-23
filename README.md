# HCL Connections Homepage Application

| Repository                  | Type | Squad   | Status | Git Repo |
| ---                         | ---  | ---     | ---    |      --: |
|[Homepage](https://jenkins.cwp.pnp-hcl.com/cnx/job/Core/job/Blue/job/IC10.0/job/IC10.0_Homepage/) | Java | Gaia | [![Build Status](https://jenkins.cwp.pnp-hcl.com/cnx/buildStatus/icon?job=Core%2FBlue%2FIC10.0%2FIC10.0_Homepage)](https://jenkins.cwp.pnp-hcl.com/cnx/job/Core/job/Blue/job/IC10.0/job/IC10.0_Homepage/) | [Repository](https://git.cwp.pnp-hcl.com/ic/homepage) |

[![Product Name Screen Shot][product-screenshot][homepage-cpbvt]]

<!-- GETTING STARTED -->
## Getting Started

To get started with working with this project, create your own fork and clone the master branch to your local machine.


## Prerequisites

To build the Homepage project, make sure that environment is configured to build using UTLS. See [Configuring an Environment to Build Using UTLS.docx](https://stage.cnx.cwp.pnp-hcl.com/communities/service/html/communityview?communityUuid=3b9e3a0e-5f44-46aa-a85c-029eaa0b1a9f#fullpageWidgetId=We01423b8dec9_495e_823e_258588e0c735&file=074b0874-70f5-4510-8f5e-ea982b49038e).

After setting this up, run the bootUTLS script against the local repository.


## Building Homepage

Navigate to homepage/sn.homepage/lwp and run
```
wsbld -l build.log
``` 

After the command has completed, check the build.log file for Errors. A successful build contains the following lines at the end of the log:
```
[10:00:55:811/sn.homepage/development.fe/buildFE] Finished Building FE [ sn.homepage ]
...
BUILD SUCCESSFUL
``` 

A log containing the word `error` or statements like below indicate the build failed and some changes are required
```
[12:01:37:246/sn.homepage/gen.build.artifacts/genBFI] Build failure detected: Missing build.status file indicates component [ homepage.tests ] failed to build.
```


The EAR archive for distribution/testing can be found at
`homepage/sn.homepage/lwp/build/dboard.ear/ear.prod/lib/dboard.ear`

This can then be used to deploy directly to WebSphere through the WAS console.

### Running a clean build

Homepage caches build information inside the build folder, and won't rebuild files even if changes were made.
To rebuild the project, run the following commands before rebuilding:
```
wsbld clean -l clean.log
rm -rf build/
``` 

## Testing

To run the Homepage unit tests, navigate to `homepage/sn.homepage/lwp/homepage.tests` and run the following command
```
wsbld run-unit-tests -l tests.log
```

Check the bottom of the tests.log file for the test results. A successful build requires all tests to pass (or skip), and none to fail or error out:
```
[10:01:41:749/homepage.tests/run-unit-tests/junit] Tests run: 192, Failures: 0, Errors: 6, Skipped: 1, Time elapsed: 24.192 sec
``` 

In case of errors, check the `TEST-com.ibm.lconn.homepage.test.TestAllJUnit4.xml` file for specifics on which test cases failed and initial indicators of errors.


## Adding code changes

When adding code changes, run both the Homepage build and tests to ensure there are no regressions in code quality.
Preferably (depending on gravity of change), deploy your new build to a test server and make sure no errors come up either in the UI or the server SystemOut.log.

After confirming local changes lead to the required results, do the following to finish your task:
- Commit your changes
    - Always add the Jira-Task ID to the commit message for traceability.
- Push your changes to your local fork and master
- Create a PR against the master repostiory
    - Always add the Jira-Task to the PR title for traceability
- Ask for peer reviews within the Gaia-squad
- Ask the Git Merge Requests channel for merging the changes/PR

### Confirming changes are promoted

To ensure the changes are fine and promote correctly, make sure that...
- The [Build pipeline](https://jenkins.cwp.pnp-hcl.com/cnx/job/Core/job/Blue/job/IC10.0/job/IC10.0_Homepage/) passes in the run related to the commit
- The [Unit test pipeline](https://devjenkins.cnx.cwp.pnp-hcl.com/job/JenkinsBlue/job/homepage/job/master/) passes and all tests remain successful
- A new version of the Homepage application is bundled into the Connections build
    - Check the [Build Overview](https://icautomation.cnx.cwp.pnp-hcl.com/) and the version time stamps to ensure a new version was picked up
- Check the changes on our [CPBVT Environment](https://cpbvtproxy.cnx.cwp.pnp-hcl.com/homepage) to confirm they have been applied

### Creating bug-fixes for older versions of Connections

Once the build and promoting has been successful, make sure to backport the changes to older versions of Connections that may require the fix. To do this, 
- Check out the necessary branches (e.g. [IC6.5CR1](https://git.cwp.pnp-hcl.com/ic/homepage/tree/IC6.5_CR1) and [IC7.0](https://git.cwp.pnp-hcl.com/ic/homepage/tree/IC7.0))
- Cherry pick the required changes from master to the branch
- Go through the steps in **Adding code changes** and **Confirming changes are promoted** in the respective pipelines to ensure promotion is successful
- Confirming changes with support staff to ensure that fixes are contained in new CFix versions and will make it to the customers