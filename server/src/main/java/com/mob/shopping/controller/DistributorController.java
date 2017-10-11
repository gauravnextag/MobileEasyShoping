package com.mob.shopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.service.DistributorServices;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;

@RestController
public class DistributorController {

	private static final Logger logger = LoggerFactory.getLogger(DistributorController.class);

	@Autowired
	private DistributorServices distributorServices;

	//@Autowired
	//private RegionDao regionDao;

	//@Autowired
	//private DistributorDao distributorDao;

	@RequestMapping(value = "/getDistributors/{districtId}", method = RequestMethod.GET,produces={"application/json"})
	@ResponseBody
	public ResponseEntity<RestResponse<List<Distributor>>> getDistributors(@PathVariable Long districtId)
			throws BaseApplicationException {
		String method = "[CONTROLLER] getDistributors>>>> DistributorListRequest :: " + districtId;
		logger.info(method);
		return RestUtils.successResponse(distributorServices.getDistributors(districtId));
	}
//
//	@RequestMapping(value = "dist/saveAll", method = RequestMethod.GET,produces={"application/json"})
//	@ResponseBody
//	public ResponseEntity<RestResponse<Boolean>> saveAll() throws BaseApplicationException {
//		saveAllDist();
//		return RestUtils.successResponse(Boolean.TRUE);
//	}
//
//	private void saveAllDist() {
//		String path = "/Users/b0200200/Desktop/test.xlsx";
//		List<Distributor> distributorsList = new ArrayList<Distributor>();
//		HashMap<String, State> statesMap = new HashMap<String, State>();
//		HashMap<String, District> districtMap = new HashMap<String, District>();
//		List<Integer> list = new ArrayList<Integer>();
//
//		FileInputStream inputStream = null;
//		try {
//			inputStream = new FileInputStream(new File(path));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		Workbook workbook = null;
//		try {
//			workbook = new XSSFWorkbook(inputStream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Sheet firstSheet = workbook.getSheetAt(0);
//		Iterator<Row> iterator = firstSheet.iterator();
//		int count = 0;
//
//		// to bypass headers
//		iterator.next();
//
//		while (iterator.hasNext()) {
//			count++;
//			try {
//
//				Row nextRow = iterator.next();
//				String region = String.valueOf(nextRow.getCell(0)) + "".trim();
//				String sdMdId = String.valueOf(nextRow.getCell(1)) + "".trim();
//				String srdMdName = String.valueOf(nextRow.getCell(2)) + "".trim();
//				String ownerName = String.valueOf(nextRow.getCell(3)) + "".trim();
//				String msisdn = String.valueOf(nextRow.getCell(4)) + "".trim();
//				String email = String.valueOf(nextRow.getCell(5)) + "".trim();
//				String srdMdType = String.valueOf(nextRow.getCell(6)) + "".trim();
//				String city = String.valueOf(nextRow.getCell(7));
//				String districtName = String.valueOf(nextRow.getCell(8)) + "".trim();
//				String state = String.valueOf(nextRow.getCell(9)) + "".trim();
//				String address = city + ", " + districtName + ", " + state;
//
//				Long time = (DateTime.now().getMillis());
//				Timestamp timestmp = new Timestamp(time);
//				State stateObj = new State();
//				stateObj.setName(state);
//				stateObj.setDisplayName(state);
//				stateObj.setCreatedDate(timestmp);
//				stateObj.setLastModifiedDate(timestmp);
//				stateObj.setIsDeleted(0);
//
//				if (statesMap.containsKey(stateObj.getName())) {
//					stateObj = statesMap.get(stateObj.getName());
//				} else {
//					stateObj = regionDao.findOrSaveState(stateObj);
//					statesMap.put(stateObj.getName(), stateObj);
//				}
//
//				District districtObj = new District();
//				districtObj.setDisplayName(districtName);
//				districtObj.setName(districtName);
//				districtObj.setStateId(stateObj.getId());
//				districtObj.setCreatedDate(timestmp);
//				districtObj.setLastModifiedDate(timestmp);
//				districtObj.setIsDeleted(0);
//
//				if (districtMap.containsKey(districtObj.getName())) {
//					districtObj = districtMap.get(districtObj.getName());
//				} else {
//					districtObj = regionDao.findOrSaveDistrict(districtObj);
//					districtMap.put(districtObj.getName(), districtObj);
//				}
//
//				Distributor distributor = new Distributor();
//				distributor.setName(ownerName);
//				distributor.setDistrictId(districtObj.getId());
//				distributor.setMsisdn(msisdn);
//				distributor.setAddress(address);
//				distributor.setRegion(region);
//				distributor.setCity(city);
//				distributor.setSrdMdId(sdMdId);
//				distributor.setSrdMdType(srdMdType);
//				distributor.setSrdMdName(srdMdName);
//				distributor.setEmail(email);
//				distributor.setCreatedDate(timestmp);
//				distributor.setLastModifiedDate(timestmp);
//				distributor.setIsDeleted(0);
//
//				distributorDao.save(distributor);
//				distributorsList.add(distributor);
//				System.out.println("processing  >> " + count);
//			} catch (Exception e) {
//				list.add(count);
//				System.out.println("Failed Row   >> " + count);
//
//			}
//
//		}
//
//		System.out.println("processed  >> " + distributorsList.size());
//		System.out.println("failed  >> " + Arrays.asList(list));
//
//	}

}
