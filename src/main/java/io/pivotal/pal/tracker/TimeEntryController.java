package io.pivotal.pal.tracker;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
	
	private TimeEntryRepository timeEntryRepository;

	public TimeEntryController(TimeEntryRepository timeEntryRepository) {
		this.timeEntryRepository = timeEntryRepository;
	}

	@PostMapping
	public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
		TimeEntry timeEntryCreated = timeEntryRepository.create(timeEntryToCreate);
		return new ResponseEntity<TimeEntry>(timeEntryCreated, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
		TimeEntry timeEntrySearch = timeEntryRepository.find(id);
		if(null != timeEntrySearch) {
			return new ResponseEntity<TimeEntry>(timeEntrySearch, HttpStatus.OK);
		} else {
			return new ResponseEntity<TimeEntry>(timeEntrySearch, HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping
	public ResponseEntity<List<TimeEntry>> list() {
		
		return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntryToUpdate) {
		TimeEntry timeEntryUpdated = timeEntryRepository.update(id, timeEntryToUpdate);
		
		if(timeEntryUpdated != null) {
			return new ResponseEntity<TimeEntry>(timeEntryUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<TimeEntry>(timeEntryUpdated, HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("{id}")
	public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
		timeEntryRepository.delete(id);
		return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
	}

}
