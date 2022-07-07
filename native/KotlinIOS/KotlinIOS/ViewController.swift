import UIKit
import SharedCode

class ViewController: UIViewController {

    @IBOutlet var fromStationButton: UIButton!
    @IBOutlet var toStationButton: UIButton!

    @IBAction func swapButtonClick(_ sender: Any) {
        (toStation, fromStation) = (fromStation, toStation)
        updateButtonTitles()
    }
    
    private var fromStation = UserStationSelection()
    private var toStation = UserStationSelection()
    private var validStations: [JourneyStation] = []

    @IBAction func fromStationButtonPressed(_ sender: Any) {
        presentSearchPage(currentSelection: fromStation)
    }

    @IBAction func toStationButtonPressed(_ sender: Any) {
        presentSearchPage(currentSelection: toStation)
    }

    @IBOutlet var fareSearchButton: UIButton!
    @IBOutlet var searchStatusBar: UILabel!
    @IBOutlet var trainsTable: UITableView!


    @IBAction func trainSearchButtonPressed(_ sender: Any) {
        searchStatusBar.text = "searching ..."
        validTrains = []
        trainsTable.reloadData()
        presenter.onSearchClicked(initialStation: fromStation.station!.crsCode,
                                  ultimateStation: toStation.station!.crsCode,
                                  timeUTCString: nil)
    }

    var stationList: [String] = []
    var validTrains: [JourneyTableDataElem] = []

    private let presenter: ApplicationContractPresenter = ApplicationPresenter()

    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        setupTable()
    }

    private func updateButtonTitles() {
        fromStationButton.setTitle("From: \(fromStation.station?.fullName ?? "")", for: .normal)
        toStationButton.setTitle("To: \(toStation.station?.fullName ?? "")", for: .normal)
    }

}

extension ViewController: UITableViewDataSource, UITableViewDelegate {
    private func setupTable() {
        self.trainsTable.dataSource = self
        self.trainsTable.delegate = self
        self.registerTableViewCells()
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.validTrains.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "customTableViewCell") as? customTableViewCell {
            cell.setJourney(journey: validTrains[indexPath.row])
            return cell
        }
        return UITableViewCell()
    }

    private func registerTableViewCells() {
        let textFieldCell = UINib(nibName: "customTableViewCell", bundle: nil)
        self.trainsTable.register(textFieldCell, forCellReuseIdentifier: "customTableViewCell")
    }
}

extension ViewController :SearchPageViewControllerDelegate{
    func filterSearchResults(query: String) -> [JourneyStation] {
        return presenter.refineSearchResults(query: query, original: validStations)
    }

    func pageDismissed(){
        print(fromStation.station)
        updateButtonTitles()
    }

    func presentSearchPage(currentSelection: UserStationSelection) {
        let popupTest = SearchPageViewController.createSearchPage(stationList: validStations, currentSelection: currentSelection)
        popupTest.delegate = self
        self.present(popupTest, animated: true, completion: nil)
    }
}


extension ViewController: ApplicationContractView {
    func populateStationList(stations: [JourneyStation]) {
        // TODO: do properly
        validStations = stations
        populateStationList(stations: stations.map({ station in station.crsCode }))
    }

    func populateStationList(stations: [String]) {
        stationList = stations
    }

    func showResults(result: [JourneyTableDataElem]) {
        validTrains = result
        if validTrains.isEmpty {
            searchStatusBar.text = "no trains available"
        } else {
            searchStatusBar.text = ""
        }
        trainsTable.reloadData()

    }

    func showAlert(msg: String) {
        searchStatusBar.text = ""
        let alert = UIAlertController(title: "Warning", message: msg, preferredStyle: UIAlertController.Style.alert)
        alert.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }

    func showResults(result: String) {
        // TODO: not yet implemented
    }

    func setLabel(text: String) {
        // TODO: not yet implemented
    }
}
