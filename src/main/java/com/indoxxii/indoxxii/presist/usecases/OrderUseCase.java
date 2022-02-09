package com.indoxxii.indoxxii.presist.usecases;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.indoxxii.indoxxii.applications.exception.OrderAlreadyPaidException;
import com.indoxxii.indoxxii.applications.request.order.OrderCreateDto;
import com.indoxxii.indoxxii.applications.request.order.OrderUpdateDto;
import com.indoxxii.indoxxii.presist.models.Movie;
import com.indoxxii.indoxxii.presist.models.Order;
import com.indoxxii.indoxxii.presist.models.OrderStatus;
import com.indoxxii.indoxxii.presist.models.User;
import com.indoxxii.indoxxii.presist.repositories.MovieRepository;
import com.indoxxii.indoxxii.presist.repositories.OrderRepository;
import com.indoxxii.indoxxii.presist.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderUseCase {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;
    
    public Order create(OrderCreateDto dto) throws Exception {

        var newOrder = new Order();
        int id_user=dto.getUsers_id();
        
        Optional<User> user=userRepository.findById(id_user);
        if(!user.isEmpty()){
            newOrder.setUser(user.get());
        }
        newOrder.setNoinvoice(this.generateNewNoInvoice());
        newOrder.setDatecreated(Date.from(Instant.now()));
        newOrder.setPaymentmethod(dto.getPaymentmethod());
        newOrder.setOrderstatus(dto.getOrderstatus());
        List<Movie>movies=movieRepository.findAllById(dto.getMovies());
        if(movies!=null){
            Set<Movie> moviesSet=new HashSet<Movie>(movies);
            newOrder.setMovies(moviesSet);
            newOrder.setTotalprice(calculateTotalPrice(moviesSet));
        }
        

        return orderRepository.save(newOrder);
    }

    private float calculateTotalPrice(Set<Movie> moviesSet){
        float totalprice = 0;
        for (Movie movie : moviesSet) {
            totalprice += movie.getPrice();
        }
        return totalprice;
    }

    public Order update(OrderUpdateDto orderUpdateDto,Integer idorders) throws Exception{
        Optional<Order> optionalOrder=orderRepository.findById(idorders);
        Order order=optionalOrder.get();
        if(order.getOrderstatus().equals(OrderStatus.PAID)){
            throw new OrderAlreadyPaidException();
        }
        order.setOrderstatus(orderUpdateDto.getOrderstatus());

        return orderRepository.save(order);
    }

    public Order findById(Integer idorders){
        Optional<Order> optionalOrder=orderRepository.findById(idorders);
        Order order=optionalOrder.get();

        return order;
    }

    public Set<Order> findAll() {
        List<Order> listOrder = orderRepository.findAll();

        return new HashSet<Order>(listOrder);
    }


    private String generateNewNoInvoice(){
        String newNoInvoice="";
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String prefix=now.format(formatter);
        String strMaxNoInvoice=orderRepository.findMaxNoInvoice(prefix);
        int numbMaxNoInvoice=1;
        if(strMaxNoInvoice!=null){
            numbMaxNoInvoice=Integer.parseInt(strMaxNoInvoice.substring(8));
            numbMaxNoInvoice++;
        }
        newNoInvoice=prefix+String.format("%03d",numbMaxNoInvoice);

        return newNoInvoice;
    }
}
