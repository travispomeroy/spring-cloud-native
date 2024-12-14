#Build
custom_build(
  # Name of the container image
  ref = 'catalog-service',
  #Command to build the container image
  command = './mvnw -DskipTests=true spring-boot:build-image -Dspring-boot.build-image.imageName=catalog-service -f pom.xml',
  tag = 'latest',
  # Files to watch that trigger a new build
  deps = ['pom.xml', 'src']
)

# Deploy
k8s_yaml(['k8s/deployment.yaml', 'k8s/service.yaml'])

# Manage
k8s_resource('catalog-service', port_forwards=['9001'])