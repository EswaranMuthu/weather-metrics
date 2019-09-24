**Weather Metrics :**

    To start the spring boot weather metrics application : 
            {weather-metrics project folder}/gradlew bootrun
    
    To execute Mock MVC testing & unit testing using (SPOCK framework - groovy):
            {weather-metrics project folder}/gradlew test
            
 com.weather.metrics.stat - package :
        Using "Chain of responsibility" design pattern, this package generates statistics from given 
        stat, metrics from & to date query Parameters.
        
 WeatherMetricsManager.java :  Is a service class, which provides backend support for Controller class
    
 InMemoryStorage.java : Is Threadsafe Singleton implementation, which uses concurrentHashMap 
 Data structure to store and retrive "measurement". 
    
    WeatherMetricsApplicationTests & WeatherMetricsControllerSpec : are unit testing groovy files to test.
    
**Weather metrics cucumber test :**
        
        To Start weather-metrics spring boot application :
            {weather-metrics project folder}/gradlew bootRun 
            
        Once weather-metrics spring boot application is started:
            execute weather-metrics-test (cucumber) application :
            
        To start weather-metrics-test :
        
            {weather-metrics-test project}/gradlew test
            
        
        TestDatas.java : This class converts all datatable into request strings
        
        AddMeasurementSteps.java : Step definition for save measurements
        GetMEasurementSteps.java : Step definition for get measurements      
           
     

                
            
     
            