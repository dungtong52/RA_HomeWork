package com.ra.controller;

import com.ra.model.Transaction;
import com.ra.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("transactions")
public class TransactionController {

    @ModelAttribute("transactions")
    public List<Transaction> initTransactions() {
        return new ArrayList<>();
    }

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String showTransaction(@ModelAttribute("transactions") List<Transaction> transactionList,
                                  Model model) {
        System.out.println("Lỗi ở controller list");
        model.addAttribute("transactions", transactionList);
        return "list";
    }

    @GetMapping("/add")
    public String initAddTransaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "add";
    }

    @PostMapping("/add")
    public String addTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
                                 BindingResult result,
                                 @ModelAttribute("transactions") List<Transaction> transactionList) {
        if (result.hasErrors()) {
            return "add";
        }
        transactionService.addTransaction(transactionList, transaction);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String initEditTransaction(@PathVariable("id") int id,
                                      @ModelAttribute("transactions") List<Transaction> transactionList,
                                      Model model) {
        Transaction transactionEdit = transactionService.findTransactionById(id, transactionList);
        if (transactionEdit == null) {
            return "list";
        } else {
            model.addAttribute("transaction", transactionEdit);
            return "update";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTransaction(@PathVariable("id") int id,
                                 @ModelAttribute("transactions") List<Transaction> transactionList,
                                 @Valid @ModelAttribute("transaction") Transaction transactionUpdate,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        transactionService.editTransaction(id, transactionList, transactionUpdate);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable("id") int id,
                                    @ModelAttribute("transactions") List<Transaction> transactionList) {
        transactionService.deleteTransaction(id, transactionList);
        return "redirect:/";
    }
}
