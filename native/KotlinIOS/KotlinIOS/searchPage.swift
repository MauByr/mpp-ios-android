//
//  searchPage.swift
//  KotlinIOS
//
//  Created by Gabriel Swallow on 04/07/2022.
//  Copyright © 2022 Evgeny Petrenko. All rights reserved.
//

import Foundation
import UIKit
import SharedCode

class SearchPageViewController: UIViewController {
    
    @IBOutlet var searchBar: UISearchBar!
    @IBOutlet var stationsTable: UITableView!
    
    var testingList: [String] = ["station 1", "station 2", "station 3"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
}

extension SearchPageViewController: UISearchBarDelegate {
    
}

extension SearchPageViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return testingList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: UITableViewCell.CellStyle.value1, reuseIdentifier: "Cell")
        return cell

    }


}
