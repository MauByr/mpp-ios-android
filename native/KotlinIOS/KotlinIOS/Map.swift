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
    
    override func viewDidLoad(){
        mapView.region
    }
}
