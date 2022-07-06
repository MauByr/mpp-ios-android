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
    
    private let presenter: ApplicationContractPresenter = ApplicationPresenter()

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
}

extension SearchPageViewController: UISearchBarDelegate {
    
}

extension SearchPageViewController: UITableViewDelegate, UITableViewDataSource {
    
}
