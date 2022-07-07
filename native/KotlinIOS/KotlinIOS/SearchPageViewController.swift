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
    @IBOutlet var stationTable: UITableView!

    var stationList: [JourneyStation] = []
    var currentSelection: UserStationSelection = UserStationSelection()


    weak var delegate : SearchPageViewControllerDelegate?

    override func viewDidLoad() {
        super.viewDidLoad()
        setupTable()
        setUpSearchBar()
    }

    public static func createSearchPage(stationList: [JourneyStation], currentSelection: UserStationSelection) -> SearchPageViewController {
        let newInstance = SearchPageViewController()
        newInstance.stationList = stationList
        newInstance.currentSelection = currentSelection
        return newInstance
    }
}


extension SearchPageViewController: UISearchBarDelegate {
    private func setUpSearchBar() {
        self.searchBar.delegate = self
    }

    public func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        print(searchText)
        stationList = delegate?.filterSearchResults(query: searchText) ?? []
        stationTable.reloadData()
    }
}

extension SearchPageViewController: UITableViewDelegate, UITableViewDataSource {

    private func setupTable() {
        self.stationTable.dataSource = self
        self.stationTable.delegate = self
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return stationList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: UITableViewCell.CellStyle.value1, reuseIdentifier: "Cell")
        let station = stationList[indexPath.row]
        cell.textLabel?.text = "[\(station.crsCode)] \(station.fullName)"
        return cell
    }


}
protocol SearchPageViewControllerDelegate: AnyObject{
    func filterSearchResults(query:String)->[JourneyStation]
}
