package com.carservice.CarService.config.commandLineRunner;

import com.carservice.CarService.OrderSparePart.OrderSparePart;
import com.carservice.CarService.OrderSparePart.OrderSparePartRepository;
import com.carservice.CarService.client.Client;
import com.carservice.CarService.client.ClientRepository;
import com.carservice.CarService.commission.Commission;
import com.carservice.CarService.commission.CommissionBuilder;
import com.carservice.CarService.commission.CommissionRepository;
import com.carservice.CarService.cost.Cost;
import com.carservice.CarService.cost.CostRepository;
import com.carservice.CarService.payment.Payment;
import com.carservice.CarService.payment.PaymentRepository;
import com.carservice.CarService.payment.PaymentStatus;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerRepository;
import com.carservice.CarService.sparePart.SparePart;
import com.carservice.CarService.sparePart.SparePartRepository;
import com.carservice.CarService.vehicles.Vehicle;
import com.carservice.CarService.vehicles.VehicleRepository;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerRepository;
import com.carservice.CarService.worker.WorkerRole;
import com.carservice.CarService.localOrder.LocalOrder;
import com.carservice.CarService.localOrder.LocalOrderRepository;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemRepository;
import com.carservice.CarService.externalOrder.ExternalOrderRepository;
import com.carservice.CarService.externalOrder.ExternalOrder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class CommandLineRunnerConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            ClientRepository clientRepository,
            ProducerRepository producerRepository,
            WorkerRepository workerRepository,
            VehicleRepository vehicleRepository,
            SparePartRepository sparePartRepository,
            CostRepository costRepository,
            CommissionRepository commissionRepository,
            PaymentRepository paymentRepository,
            PasswordEncoder passwordEncoder,
            OrderSparePartRepository orderSparePartRepository,
            OrderItemRepository orderItemRepository,
            LocalOrderRepository localOrderRepository,
            ExternalOrderRepository externalOrderRepository


            ){
        return args -> {
            addClients(clientRepository);
            addProducers(producerRepository);
            addVehicles(vehicleRepository);
            addWorkers(workerRepository, passwordEncoder);
            addSpareParts(sparePartRepository, producerRepository);
            addCosts(costRepository, sparePartRepository, orderSparePartRepository);
            addCommissions(commissionRepository, vehicleRepository, clientRepository, workerRepository);
            addPayments(paymentRepository, clientRepository);
            addLocalOrder(workerRepository, localOrderRepository);
        };
    }
    private void addLocalOrder(WorkerRepository workerRepository,LocalOrderRepository localOrderRepository)
    {
        List<Worker> worker = workerRepository.findAll();
        LocalDateTime createDate = LocalDateTime.now();

        LocalOrder localOrder1 = new LocalOrder(worker.get(0), createDate);

        LocalOrder localOrder2 = new LocalOrder(worker.get(1), createDate);
        localOrderRepository.saveAll(
            List.of(localOrder1, localOrder2)
        );
    }


    private void addClients(ClientRepository clientRepository) {
        Client wojtek = new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876");
        Client alex = new Client("Alex", "Maj", "alex@gmail.com", "555432897");
        Client john = new Client("John", "Doe", "john@gmail.com", "123456789");
        Client emily = new Client("Emily", "Smith", "emily@gmail.com", "987654321");
        Client anna = new Client("Anna", "Johnson", "anna@gmail.com", "456789012");
        Client robert = new Client("Robert", "Brown", "robert@gmail.com", "876543210");
        Client maria = new Client("Maria", "Lee", "maria@gmail.com", "321098765");
        Client james = new Client("James", "Taylor", "james@gmail.com", "654321098");
        Client lisa = new Client("Lisa", "Anderson", "lisa@gmail.com", "890123456");
        Client michael = new Client("Michael", "White", "michael@gmail.com", "234567890");

        clientRepository.saveAll(
                List.of(wojtek, alex, john, emily, anna, robert, maria, james, lisa, michael)
        );
    }


    private void addVehicles(VehicleRepository vehicleRepository) {
        Vehicle vehicle1 = new Vehicle("opel", "astra", "123123", "TKI 5VF5","2001");
        Vehicle vehicle2 = new Vehicle("bmw", "idk", "1231234", "KLI 6039C", "2007");

        vehicleRepository.saveAll(
                List.of(vehicle1, vehicle2)
        );
    }

    private void addProducers(ProducerRepository producerRepository) {
        Producer spareGenius = new Producer(
                "SpareGenius"
        );
        Producer smartFix = new Producer(
                "SmartFix"
        );
        Producer techSolve = new Producer(
                "TechSolve"
        );
        Producer innovateSpare = new Producer(
                "InnovateSpare"
        );

        producerRepository.saveAll(
                List.of(spareGenius, smartFix, techSolve, innovateSpare)
        );
    }

    private void addWorkers(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        Worker jan = new Worker(
                "Jan",
                "Marzec",
                "jan@gmail.com",
                "555666777",
                new BigDecimal("20.50"),
                WorkerRole.ROLE_RECEPTIONIST,
                passwordEncoder.encode("password1")
        );
        Worker piotr = new Worker(
                "Piotr",
                "Czerwiec",
                "piotr@gmail.com",
                "111567345",
                new BigDecimal("27.50"),
                WorkerRole.ROLE_MANAGER,
                passwordEncoder.encode("password2")
        );
        Worker karol = new Worker(
                "Karol",
                "Lipiec",
                "karol@gmail.com",
                "333445554",
                new BigDecimal("25.50"),
                WorkerRole.ROLE_CONTRACTOR,
                passwordEncoder.encode("password3")
        );
        Worker maciej = new Worker(
                "Maciej",
                "Listopad",
                "maciej@gmail.com",
                "231453675",
                new BigDecimal("24.50"),
                WorkerRole.ROLE_WAREHOUSEMAN,
                passwordEncoder.encode("password4")
        );

        workerRepository.saveAll(
                List.of(jan, piotr, karol, maciej)
        );
    }

    private void addSpareParts(SparePartRepository sparePartRepository, ProducerRepository producerRepository) {
        SparePart brakePad = new SparePart(
                "Brake Pad",
                new BigDecimal("30.00"),
                10,
                producerRepository.findById(1L).orElse(null),
                """
                        Producent\\tBRAKE-MAX\\n
                        Indeks\\tBMX123\\n
                        Kod EAN\\t1234567890123\\n
                        Typ klocka\\tStandardowy\\n
                        Grubość [mm]\\t15\\n
                        Materiał\\tKeramika"""
        );
        SparePart oilFilter = new SparePart(
                "Oil Filter",
                new BigDecimal("8.50"),
                10,
                producerRepository.findById(2L).orElse(null),
                """
                        Producent\\tXYZ\\n
                        Indeks\\tABC123\\n
                        Kod EAN\\t1234567890123\\n
                        Typ filtra\\tFiltr oleju\\n
                        Wysokość [mm]\\t90\\n
                        Wysokość 2 [mm]\\t85,5
                        """
        );
        SparePart sparkPlug = new SparePart(
                "Spark Plug",
                new BigDecimal("4.00"),
                10,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\\tIGNITE-PLUS\\n
                        Indeks\\tIP123\\n
                        Kod EAN\\t1234567890123\\n
                        Typ świecy\\tStandardowa\\n
                        Rozmiar gwintu\\tM14x1.25\\n
                        Elektroda odstająca [mm]\\t0,8
                        """
        );
        SparePart airFilter = new SparePart(
                "Air filter",
                new BigDecimal("4.00"),
                50,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\\tACME\\n
                        Indeks\\tXY789\\n
                        Kod EAN\\t9876543210987\\n
                        Typ filtra\\tFiltr powietrza\\n
                        Wysokość [mm]\\t150\\n
                        Wysokość 2 [mm]\\t145,2
                        """
        );

        SparePart sparePart1 = new SparePart(
                "Engine",
                new BigDecimal("4.00"),
                50,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\\tECOBOOST\\n
                        Numer modelu\\tEB456\\n
                        Typ silnika\\tBenzynowy\\n
                        Pojemność skokowa [cm³]\\t1600\\n
                        Moc [kW]\\t120\\n
                        Maksymalny moment obrotowy [Nm]\\t200
                        """
        );
        SparePart sparePart2 = new SparePart(
                "Tire",
                new BigDecimal("4.00"),
                50,
                producerRepository.findById(3L).orElse(null),
                """
                        Producent\\tTIRE-MAX\\n
                        Indeks opony\\tTM123\\n
                        Rozmiar\\t205/55R16\\n
                        Typ opony\\tLetnia\\n
                        Indeks prędkości\\tH\\n
                        Indeks nośności\\t91
                        """
        );

        sparePartRepository.saveAll(
                List.of(brakePad, oilFilter, sparkPlug, airFilter, sparePart1, sparePart2)
        );
    }

    private void addCosts(
            CostRepository costRepository,
            SparePartRepository sparePartRepository,
            OrderSparePartRepository orderSparePartRepository
    ) {
        List<SparePart> spareParts = sparePartRepository.findAll();

        OrderSparePart orderSparePart1 = new OrderSparePart(spareParts.get(0), 5);
        OrderSparePart orderSparePart2 = new OrderSparePart(spareParts.get(1), 7);
        OrderSparePart orderSparePart3 = new OrderSparePart(spareParts.get(2), 3);
        OrderSparePart orderSparePart4 = new OrderSparePart(spareParts.get(3), 10);

        orderSparePartRepository.saveAll(List.of(orderSparePart1, orderSparePart2, orderSparePart3, orderSparePart4));

        List<OrderSparePart> orderSpareParts = orderSparePartRepository.findAll();

        List<Cost> costs = List.of(
                new Cost(
                        "Cost 1",
                        LocalDateTime.now(),
                        orderSpareParts.subList(0, 2),
                        new BigDecimal("50.00")
                ),
                new Cost(
                        "Cost 2",
                        LocalDateTime.now(),
                        orderSpareParts.subList(2, 3),
                        new BigDecimal("30.00")
                )
        );

        costRepository.saveAll(costs);
    }

    public void addCommissions(
            CommissionRepository commissionRepository,
            VehicleRepository vehicleRepository,
            ClientRepository clientRepository,
            WorkerRepository workerRepository
    ) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        List<Worker> workers = workerRepository.findAll();

        Commission commission1 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(0))
                .buildClient(clients.get(0))
                .buildWorker(workers.get(0))
                .buildDescription("Commission 1")
                .build();

        Commission commission2 = CommissionBuilder.getBase()
                .buildVehicle(vehicles.get(1))
                .buildClient(clients.get(1))
                .buildWorker(workers.get(1))
                .buildDescription("Commission 2")
                .build();


        commissionRepository.saveAll(List.of(commission1, commission2));
    }

    private void addPayments(
            PaymentRepository paymentRepository,
            ClientRepository clientRepository
    ) {
        List<Client> clients = clientRepository.findAll();

        Payment payment1 = new Payment();
        payment1.setAmount(new BigDecimal("50.00"));
        payment1.setClient(clients.get(0));
        payment1.setPaymentStatus(PaymentStatus.PENDING);

        Payment payment2 = new Payment();
        payment2.setAmount(new BigDecimal("30.00"));
        payment2.setClient(clients.get(1));
        payment2.setPaymentStatus(PaymentStatus.EXPIRED);

        paymentRepository.saveAll(List.of(payment1, payment2));
    }
}
