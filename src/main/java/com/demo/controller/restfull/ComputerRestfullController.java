package com.demo.controller.restfull;

import com.demo.model.Category;
import com.demo.model.Computer;
import com.demo.service.ICategoryService;
import com.demo.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/computers")
public class ComputerRestfullController {
    @Autowired
    IComputerService computerService;

    @GetMapping
    public ResponseEntity<Iterable<Computer>> finAllComputer() {
        List<Computer> computers = (List<Computer>) computerService.findAll();
        if (computers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Computer>> findById(@PathVariable("id") Long id) {
        Optional<Computer> computer = computerService.findById(id);
        if (!computer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Computer> saveComputer(@RequestBody Computer computer) {
        computerService.save(computer);
        return new ResponseEntity<>(computer, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<Computer>> updateComputer(@PathVariable("id") Long id, @RequestBody Computer computer) {
        Optional<Computer> computerUpdate = computerService.findById(id);
        if (!computerUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computer.setId(computerUpdate.get().getId());
        computerService.save(computer);
        return new ResponseEntity<>(Optional.of(computer), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Optional<Computer>> deleteComputer(@PathVariable("id") Long id){
    Optional<Computer> computerDelete = computerService.findById(id);
    if(!computerDelete.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    computerService.remove(id);
    return  new ResponseEntity<>(computerDelete,HttpStatus.OK);
    }
}
