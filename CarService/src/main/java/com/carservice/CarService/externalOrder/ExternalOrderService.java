package com.carservice.CarService.externalOrder;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.order.OrderStatus;
import com.carservice.CarService.orderItem.OrderItem;
import com.carservice.CarService.orderItem.OrderItemDTO;
import com.carservice.CarService.orderItem.OrderItemService;
import com.carservice.CarService.producer.Producer;
import com.carservice.CarService.producer.ProducerService;
import com.carservice.CarService.wholesaler.WholesalerAdapterIPARTS;
import com.carservice.CarService.worker.Worker;
import com.carservice.CarService.worker.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
//@Service
//public class ExternalOrderService {
//
//    private final ExternalOrderRepository externalOrderRepository;
//
//
//    public List<ExternalOrder> getAllExternalOrders(){
//        return externalOrderRepository.findAll();
//    }
//
//    private ExternalOrder createExternalOrder(Long)
//
//    public Long addOrderItemToExternalOrder(Long orderItemId, CreateExternalOrderRequest externalOrderRequest){
//        ExternalOrder externalOrder;
//
//        if(externalOrderRequest.externalOrderId() == null){
//            externalOrder =
//        }
//    }
//}

@RequiredArgsConstructor
@Service
public class ExternalOrderService {
    private final ExternalOrderRepository externalOrderRepository;
    private final WorkerService workerService;
    private final OrderItemService orderItemService;
    private final ProducerService producerService;
//    private final OrderSparePartService orderSparePartService;
    private final WholesalerAdapterIPARTS wholesalerAdapterIPARTS;

    private final ExternalOrderMapper externalOrderMapper;


    public List<ExternalOrderDTO> getAllExternalOrders() {
        List<ExternalOrder> externalOrders = externalOrderRepository.findAll();
        return externalOrders.stream().map(externalOrderMapper::map).collect(Collectors.toList());
    }

    public ExternalOrder getExternalOrderByWorkerId(Long workerId) {
        List<ExternalOrder> externalOrders = externalOrderRepository.findByWorkerId(workerId);
        if(externalOrders.isEmpty())
            return null;
        return externalOrders.get(0);
    }

    // tworzy zamówienie
    private ExternalOrder createExternalOrder(Long workerId) {
        Worker worker = workerService.getWorkerEntityById(workerId);
        ExternalOrder externalOrder = new ExternalOrder(worker, LocalDateTime.now());

        ExternalOrder saved = externalOrderRepository.save(externalOrder);
        externalOrder.submitOrder();
        return saved;
    }

    // TODO change 'add item functionality' if worker has open order with status NEW, just add item to it without providing externalOrderId
    public Long addItemToExternalOrder(CreateExternalOrderRequest externalOrderRequest, OrderItemDTO orderItemDTO, Long wholesalerPartId) {

        final Worker worker = workerService.getWorkerByEmail(externalOrderRequest.email());

        ExternalOrder workerExternalOrder = getExternalOrderByWorkerId(worker.getId());

        if(orderItemDTO.quantity() < 1) {
            throw new ResourceNotFoundException("Order Item with id [%s] not available".formatted(orderItemDTO.id()));
        }

        ExternalOrder externalOrder;
        if(workerExternalOrder != null && workerExternalOrder.getOrderStatus() == OrderStatus.CREATING) {
            externalOrder = workerExternalOrder;
        } else {
            externalOrder = createExternalOrder(worker.getId());
        }

        List<OrderItem> tmpList = externalOrder.getItems();
        Producer producer = null;
        if(orderItemDTO.producerId() != null)
            producer = producerService.getProducerById(orderItemDTO.producerId());

        OrderItem orderItem = orderItemService.createOrderItem(
                new OrderItem(
                        orderItemDTO.sparePartName(),
                        orderItemDTO.price(),
                        externalOrderRequest.quantity(),
                        producer,
                        orderItemDTO.wholesaler()
                )
        );
        tmpList.add(orderItem);

        // dodanie częśći zamiennej do listy
        externalOrder.setItems(tmpList);
        externalOrderRepository.save(externalOrder);

        wholesalerAdapterIPARTS.put(wholesalerPartId, externalOrderRequest.quantity());

        return externalOrder.getId();
    }

//    public void updateExternalOrder(Long externalOrderId, UpdateExternalOrderRequest externalOrderRequest) {
//        ExternalOrder updateExternalOrder = externalOrderRepository.findById(externalOrderId)
//                .orElseThrow(() -> new ResourceNotFoundException("ExternalOrder with id [%s] not found.".formatted(externalOrderId)));
//
//        externalOrderRepository.save(updateExternalOrder);
//    }
}