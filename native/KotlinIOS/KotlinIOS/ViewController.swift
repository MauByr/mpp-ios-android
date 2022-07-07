import UIKit
import SharedCode

class ViewController: UIViewController {
    @IBOutlet var departureStationScrollWheel: UIPickerView!
    @IBOutlet var arrivalStationScrollWheel: UIPickerView!

    @IBOutlet var fareSearchButton: UIButton!
    @IBOutlet var searchStatusBar: UILabel!
    @IBOutlet var trainsTable: UITableView!
    
    @IBAction func tesingButton(_ sender: Any) {
        presentSearchPage()
    }
    
    @IBAction func trainSearchButtonPressed(_ sender: Any) {
        searchStatusBar.text = "searching ..."
        validTrains = []
        trainsTable.reloadData()
        
        let initialStationIndex = departureStationScrollWheel.selectedRow(inComponent: 0)
        let finalStationIndex = arrivalStationScrollWheel.selectedRow(inComponent: 0)
        presenter.onSearchClicked(initialStation: stationList[initialStationIndex],
                                  ultimateStation: stationList[finalStationIndex],
                                  timeUTCString: nil)
    }
    
    var stationList: [String] = []
    var validTrains: [JourneyTableDataElem] = []

    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        setupTable()
        setupScrollWheel()
    }
}

extension ViewController: UITableViewDataSource, UITableViewDelegate{
    private func setupTable(){
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
    
    private func registerTableViewCells() {let textFieldCell = UINib(nibName: "customTableViewCell", bundle: nil)
        self.trainsTable.register(textFieldCell, forCellReuseIdentifier: "customTableViewCell")
    }
}

extension ViewController {
    func presentSearchPage(){
        let popupTest = SearchPageViewController()
        self.present(popupTest, animated: true, completion: nil)
    }
}

extension ViewController: UIPickerViewDataSource, UIPickerViewDelegate {
    private func setupScrollWheel(){
        self.departureStationScrollWheel.dataSource = self
        self.departureStationScrollWheel.delegate = self
        
        self.arrivalStationScrollWheel.dataSource = self
        self.arrivalStationScrollWheel.delegate = self
        
        populateStationList(stations: stationList)
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return stationList.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
         return stationList[row]
    }
}

extension ViewController: ApplicationContractView {
    func populateStationList(stations: [JourneyStation]) {
        // TODO: do properly
        populateStationList(stations: stations.map({ station in station.crsCode}))
        
    }
    
    func populateStationList(stations: [String]) {
        stationList = stations
        departureStationScrollWheel.reloadAllComponents()
        arrivalStationScrollWheel.reloadAllComponents()
    }
    
    func showResults(result: [JourneyTableDataElem]) {
        validTrains = result
        if validTrains.isEmpty {
            searchStatusBar.text = "no trains available"
        }
        else {
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
