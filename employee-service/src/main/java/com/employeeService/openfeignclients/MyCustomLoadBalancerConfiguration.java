package com.employeeService.openfeignclients;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class MyCustomLoadBalancerConfiguration {

	@Bean
	ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment env,
			LoadBalancerClientFactory loadBalancerClientFactory)
	{
		String name = env.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		
		return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
	}
	
}
