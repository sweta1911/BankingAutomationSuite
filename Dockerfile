# Use Maven with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory
WORKDIR /app

# ---------------------
# Install Chrome browser
# ---------------------
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    unzip \
    wget \
    --no-install-recommends && \
    rm -rf /var/lib/apt/lists/*

RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable

# ---------------------
# Copy project and run tests
# ---------------------

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project
COPY . .

# ---------------------
# Run tests (TestNG)
# ---------------------
CMD ["mvn", "clean", "test"]
