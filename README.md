
Alfresco Deleted Content Store Cleaner
================================================

This add-on provide a job to remove abandoned files in Deleted Content Store. 

From Alfresco 5.2 (201707 GA) a **Trashcan Cleaner** job is provided out of the box, although is disabled by default. This job removes documents from trash can periodically. 

Once a content has been deleted from trashcan, `ContentStoreCleaner` job moves it to Deleted Content Store and change node state to `deleted` in database 14 days after. `NodeServiceCleanup` job remove the node from database 30 days after, but in the end the file is still living in Deleted Content Store. This addon will purge from Deleted Content Store (usually at `${alfresco}/alf_data/contentstore.deleted`) these abandoned files.

Configuring both Trashcan Cleaner and Deleted Content Store Cleaner addons, contents will be removed phisically from content stores without user or admin operation.

![deleted-content-store-cleaner](https://user-images.githubusercontent.com/5584952/29554931-3f2efb64-8721-11e7-9994-2c3b9f4f2a5e.png)

**License**
The plugin is licensed under the [LGPL v3.0](http://www.gnu.org/licenses/lgpl-3.0.html). 

**State**
Current addon release is 1.0.0

**Compatibility**
The current version has been developed using Alfresco 201702 and Alfresco SDK 3.0.1

***No original Alfresco resources have been overwritten***

Downloading the ready-to-deploy-plugin
--------------------------------------
The binary distribution is made of one JAR file to be deployed in Alfresco as a repo module:

* [repo JAR](https://github.com/keensoft/alfresco-deleted-content-store-cleaner/releases/download/1.0.0/deleted-content-store-cleaner-1.0.0.jar)

You can install it by copying JAR file to `$ALFRESCO_HOME/modules/platform` and re-starting Alfresco.


Building the artifacts
----------------------
You can build the artifacts from source code using maven
```$ mvn clean package```

Configuration
-------------
Job can be planified by including following property in `alfresco-global.properties`
```
system.content.deleted.cronExpression=0 0 1 * * ?
```
By default, it will run every night at 1 AM.
