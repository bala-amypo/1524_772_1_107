@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService service;

    public ParcelController(ParcelService service) {
        this.service = service;
    }

    @PostMapping
    public Parcel add(@RequestBody Parcel parcel) {
        return service.addParcel(parcel);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public Parcel get(@PathVariable String trackingNumber) {
        return service.getByTrackingNumber(trackingNumber);
    }
}

