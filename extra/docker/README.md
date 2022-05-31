A few examples on how you can create Docker images from your project.

docker1: No Maven plugin used, just a `Dockerfile` that can be used to build the image using docker client.
docker2: `fabric8` plugin and 2 options to generate the Docker image (pure XML or based on Dockerfile)
docker3: JKube maven plugin, successor of fabric8, support for Kubernetes and 'vendor neutral' from Eclipse Foundation. But the Kubernetes part only works for Redhat (WildFly, Quarkus), OpenLiberty,  SpringBoot, Jetty. Not standard Jakarta EE like Payara Micro.
