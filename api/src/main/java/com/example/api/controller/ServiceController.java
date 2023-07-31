package com.example.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Service;


@RestController
@RequestMapping("/api/service")
public class ServiceController {

	private List<Service> services = new ArrayList<>();

	@PostMapping("/provision")
	public ResponseEntity<Service> provisionService(@RequestBody Service service) {
		// Logic to provision the service and generate a unique connectionId
		service.setActive(true);
		services.add(service);
		return ResponseEntity.status(HttpStatus.CREATED).body(service);
	}

	@PostMapping("/test-qos")
	public ResponseEntity<String> testQoS(@RequestBody Service service) {
		// Logic to test the quality of service (QoS) for the given service
		if (service.isActive()) {
			return ResponseEntity.ok("QoS test successful.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Service is not active.");
		}
	}

	@PutMapping("/disable/{connectionId}")
	public ResponseEntity<String> disableService(@PathVariable String connectionId) {
		// Logic to disable the service with the provided connectionId
		Service service = findServiceByConnectionId(connectionId);
		if (service != null) {
			service.setActive(false);
			return ResponseEntity.ok("Service disabled successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
		}
	}

	@PutMapping("/hold/{connectionId}")
	public ResponseEntity<String> holdService(@PathVariable String connectionId) {
		// Logic to put the service with the provided connectionId on hold
		Service service = findServiceByConnectionId(connectionId);
		if (service != null) {
			// Additional logic for putting the service on hold
			return ResponseEntity.ok("Service put on hold successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
		}
	}

	@PutMapping("/resume/{connectionId}")
	public ResponseEntity<String> resumeService(@PathVariable String connectionId) {
		// Logic to resume the service with the provided connectionId
		Service service = findServiceByConnectionId(connectionId);
		if (service != null) {
			// Additional logic for resuming the service
			return ResponseEntity.ok("Service resumed successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
		}
	}

	// Helper method to find a service by connectionId
	private Service findServiceByConnectionId(String connectionId) {
		return services.stream().filter(service -> service.getConnectionId().equals(connectionId)).findFirst()
				.orElse(null);
	}
}
