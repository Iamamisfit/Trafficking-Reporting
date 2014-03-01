Trafficking-Reporting
=====================

mobile friendly website for citizens to report suspected human trafficking situations.

# To Build from source

Check out of git. From the command line, run the sbt, or sbt.bat if on windows. You should now be in a sbt console. Run 'Complile'. That should download all of the necessary libraries. You should see it report success. Next run 'container:start', it should show that it's a local server to demo the appication. In your web browser, goto "http://localhost:8080/" you should from there be able to try out the appication. 

## Deploying to Heroku

###Setup
1. Install the Heroku Toolbelt
2. Install the heroku-deploy command line plugin:
     $ heroku plugins:install https://github.com/heroku/heroku-deploy

###Commands

heroku create
heroku deploy:war --war <path_to_war_file>
heroku config:set JAVA_OPTS="-Drun.mode=production -Xmx384m -Xss512k -XX:+UseCompressedOops"
