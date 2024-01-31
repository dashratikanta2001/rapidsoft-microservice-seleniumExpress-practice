package com.employeeService.openfeignclients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

//@LoadBalancerClient(value = "ADDRESS-SERVICE", configuration = MyCustomLoadBalancerConfiguration.class)
@LoadBalancerClient(value = "ADDRESS-SERVICE")
public class AddressServiceLoadBalancer {

    @LoadBalanced
    @Bean
    Feign.Builder feiBuilder()
	{
		return Feign.builder();
	}
	
}
