import Foundation
import UIKit
import SharedCode
import MapKit

class Map: UIViewController{
    
    @IBOutlet var mapView: MKMapView!
    @IBAction func closeMapPage(_ sender: Any) {
        dismiss(animated: true)
    }
    
    
    override func viewDidLoad(){
        super.viewDidLoad()
        self.mapView.delegate = self
    }
    
        
    func displayMapData(mapData: MapData){
        addPinToMap(
            coordinates: mapData.startStation.coords, label: mapData.startStation.name)
        addPinToMap(
            coordinates: mapData.endStation.coords, label: mapData.endStation.name)
        addLineBetweenTwoPoints(
            coordinate1: mapData.startStation.coords, coordinate2: mapData.endStation.coords)
    }
    
    private func addPinToMap(coordinates: GeoCoordinate, label: String? = nil){
        let annotation = MKPointAnnotation()
        let centerCoordinate = CLLocationCoordinate2D(
            latitude: coordinates.latitude,
            longitude: coordinates.longitude)
        annotation.coordinate = centerCoordinate
        annotation.title = label
        mapView.addAnnotation(annotation)
    }
    
    private func addLineBetweenTwoPoints(coordinate1: GeoCoordinate, coordinate2: GeoCoordinate){
        let CLCoordinates: [CLLocationCoordinate2D]  =
            [CLLocationCoordinate2D(latitude: coordinate1.latitude, longitude: coordinate1.longitude),
             CLLocationCoordinate2D(latitude: coordinate2.latitude, longitude: coordinate2.longitude)]
        let polyline = MKPolyline(coordinates: CLCoordinates, count: CLCoordinates.count)
        mapView.addOverlay(polyline)
        
    }
}

extension Map: MKMapViewDelegate{
    func mapView(_ mapView: MKMapView, rendererFor overlay: MKOverlay) -> MKOverlayRenderer {
        print("in here!")

        if let routePolyline = overlay as? MKPolyline {
            let renderer = MKPolylineRenderer(polyline: routePolyline)
            renderer.strokeColor = UIColor.blue.withAlphaComponent(0.9)
            renderer.lineWidth = 7
            return renderer
        }

        return MKOverlayRenderer()
    }
}
