//
//  Map.swift
//  KotlinIOS
//
//  Created by Gabriel Swallow on 08/07/2022.
//  Copyright © 2022 Evgeny Petrenko. All rights reserved.
//

import Foundation
import UIKit
import SharedCode
import MapKit

class Map: UIViewController{
    
    @IBOutlet var mapView: MKMapView!
    @IBAction func closeMapPage(_ sender: Any) {
        dismiss(animated: true)
    }
    
    weak var delegate: MapDelegate?
    
    override func viewDidLoad(){
        super.viewDidLoad()
        
        addPinToMap(latitude: 51, longitude: 0)
    }
    
    
    private func addPinToMap(latitude: Double, longitude: Double){
        let annotation = MKPointAnnotation()
        let centerCoordinate = CLLocationCoordinate2D(latitude: latitude, longitude: longitude)
        annotation.coordinate = centerCoordinate
        annotation.title = "station name here"
        mapView.addAnnotation(annotation)
    }
//    public static func createMapPage() -> Map {
//        let newInstance = Map()
//        return newInstance
//    }
    
//    private func closeMapPage(){
//        dismiss(animated: true)
//    }
}


protocol MapDelegate: AnyObject{
    func getMapData() -> AnyObject
}
