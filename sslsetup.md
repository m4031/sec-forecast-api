# How to get a free certificate from CA authority
To obtain a free SSL certificate from a Certificate Authority (CA), you can use Let's Encrypt, a widely used CA that provides free SSL/TLS certificates. Here's a step-by-step guide on how to get a free certificate from Let's Encrypt:

Using Certbot (Let's Encrypt Client):
1- Install Certbot:

Certbot is the official client for Let's Encrypt. You can install it on your server. The installation instructions may vary depending on your operating system. Here are general steps for some common systems:
Ubuntu/Debian:
sudo apt-get update
sudo apt-get install certbot

CentOS:
sudo yum install certbot
Run Certbot:

2- After installing Certbot, run the following command to obtain and install a certificate:
sudo certbot certonly --standalone -d yourdomain.com

Replace yourdomain.com with your actual domain. Certbot will automatically download and install the certificate.
Automate Certificate Renewal:

3- Let's Encrypt certificates are valid for 90 days. It's essential to renew them periodically. Certbot can automatically handle the renewal through a cron job. You can check the automatic renewal process by running:

sudo certbot renew --dry-run
If no errors occur, the renewal process is set up correctly.

# Renew

To non-interactively renew *all* of your certificates, run
   "certbot renew"


# How to configure SSL 

Here's a step-by-step guide:

1. Obtain or Create an SSL Certificate:
Option 1: Use Let's Encrypt:

Let's Encrypt provides free SSL certificates. You can use Certbot, as mentioned in the previous response, to obtain a certificate.
Follow the steps in the "Using Certbot (Let's Encrypt Client)" section.
Option 2: Use a Self-Signed Certificate:

For development or testing purposes, you can generate a self-signed certificate using tools like OpenSSL.
Example using OpenSSL:
bash
Copy code
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
2. Configure Spring Boot to Use HTTPS:
Place the SSL certificate in a secure location accessible to your Spring Boot application.

Open your application.properties or application.yml file and add the following properties:

properties
Copy code
server.port=8443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your-password
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat
Adjust the file paths, passwords, and other settings according to your certificate.

3. Update Security Configuration (Optional):
If you are using Spring Security, you might need to update the security configuration to ensure that HTTPS is enforced. Add the following configuration to a SecurityConfig class:

java
Copy code
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requiresChannel().anyRequest().requiresSecure()
            .and()
            // Other security configurations...
    }
}
4. Run Your Spring Boot Application:
Run your Spring Boot application, and it should now be accessible over HTTPS on the specified port (in this example, 8443). You can access it by navigating to https://localhost:8443.

Important Notes:
In production, it's recommended to obtain a valid SSL certificate from a trusted Certificate Authority (CA) for security reasons.

Update your web server (e.g., Apache, Nginx) to forward requests to your Spring Boot application using HTTPS.

Always keep your SSL certificate and private key secure and confidential.

For additional security, consider configuring HSTS (HTTP Strict Transport Security) in your Spring Boot application.

Make sure to consult the Spring Boot and Spring Security documentation for any additional configuration or security considerations based on your specific use case.


#### Start Keycloak SSO app
.\kc.bat start-dev --https-key-store-file=C:/APP-DEVELOPMENT/AI/SecuritiesForecast/build/certs/keystore.p12 --https-key-store-password=XXXX
